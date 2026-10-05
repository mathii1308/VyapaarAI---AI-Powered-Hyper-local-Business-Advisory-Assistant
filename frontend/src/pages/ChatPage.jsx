import React, { useState, useEffect } from 'react';
import ChatWindow from '../components/chat/ChatWindow';
import ChatInput from '../components/chat/ChatInput';
import { fetchBusinessProfileById, sendChatMessage } from '../services/api';

export default function ChatPage({ businessId, onNavigate }) {
  const [businessProfile, setBusinessProfile] = useState(null);
  const [loadingProfile, setLoadingProfile] = useState(false);
  const [messages, setMessages] = useState([
    {
      role: 'assistant',
      content: `### Welcome to VyapaarAI Business Advisor 🤖\n\nI am your intelligent business & financial co-pilot. I analyze your declared operational figures, profit margins, and debt coverage to provide practical, step-by-step guidance.\n\n*Select a suggested question below or type your custom query to get started.*`
    }
  ]);
  const [isLoading, setIsLoading] = useState(false);

  // Load business profile context if businessId is present
  useEffect(() => {
    if (businessId) {
      const loadProfile = async () => {
        setLoadingProfile(true);
        const result = await fetchBusinessProfileById(businessId);
        if (result.success && result.data) {
          setBusinessProfile(result.data);
          // Add contextual greeting
          setMessages([
            {
              role: 'assistant',
              content: `### VyapaarAI Advisor — Profile Context Loaded 📊\n\nI have loaded the financial diagnostic report for **${result.data.businessName}** (${result.data.location}).\n\n**Quick Financial Snapshot:**\n- **Monthly Net Profit:** ₹${result.data.netMonthlyProfit?.toLocaleString('en-IN')}\n- **Profit Margin:** ${result.data.profitMarginPercent}%\n- **Health Score:** ${result.data.financialHealthScore}/100\n\nHow can I help you optimize or expand your business today?`
            }
          ]);
        }
        setLoadingProfile(false);
      };
      loadProfile();
    }
  }, [businessId]);

  const handleSendMessage = async (userText) => {
    if (!userText || isLoading) return;

    // 1. Add User message to conversation
    const newMessages = [...messages, { role: 'user', content: userText }];
    setMessages(newMessages);
    setIsLoading(true);

    // 2. Prepare payload history for context
    const historyPayload = messages.map(m => ({
      role: m.role,
      content: m.content
    }));

    // 3. Call POST /api/chat
    const result = await sendChatMessage({
      message: userText,
      businessProfileId: businessId ? Number(businessId) : (businessProfile?.id || null),
      history: historyPayload
    });

    setIsLoading(false);

    if (result.success && result.data) {
      setMessages(prev => [
        ...prev,
        {
          role: 'assistant',
          content: result.data.message,
          knowledgeSources: result.data.knowledgeSources,
          strategyRecommendations: result.data.strategyRecommendations
        }
      ]);
    } else {
      setMessages(prev => [
        ...prev,
        {
          role: 'assistant',
          content: `⚠️ ${result.error || 'AI service is temporarily unavailable. Please try again later.'}`
        }
      ]);
    }
  };

  const handleClearConversation = () => {
    setMessages([
      {
        role: 'assistant',
        content: `Conversation reset. How else can I assist your enterprise today?`
      }
    ]);
  };

  return (
    <div className="section-container" style={{ paddingTop: '1.5rem', paddingBottom: '3rem' }}>
      {/* Advisor Page Header */}
      <div className="chat-header-card glass-card mb-4">
        <div className="chat-header-title">
          <div className="avatar assistant-avatar" style={{ width: '42px', height: '42px', fontSize: '1.3rem' }}>
            🤖
          </div>
          <div>
            <h2 style={{ fontSize: '1.35rem', margin: 0 }}>VyapaarAI Advisor</h2>
            <p style={{ fontSize: '0.85rem', color: 'var(--text-muted)', margin: 0 }}>
              AI Business Advisory & Financial Structuring Co-Pilot
            </p>
          </div>
        </div>

        <div className="chat-header-actions">
          {businessProfile ? (
            <div className="context-badge">
              <span className="badge-dot"></span>
              <span>Context: <strong>{businessProfile.businessName}</strong></span>
            </div>
          ) : (
            <div className="context-badge offline-badge">
              <span>General Advisory Mode</span>
            </div>
          )}

          {onNavigate && (
            <button className="btn-secondary btn-sm" onClick={() => onNavigate('profile')}>
              Switch Profile
            </button>
          )}

          <button className="btn-secondary btn-sm" onClick={handleClearConversation}>
            Clear Chat
          </button>
        </div>
      </div>

      {/* Main Chat Interface */}
      <div className="chat-interface-wrapper glass-card">
        <ChatWindow
          messages={messages}
          isLoading={isLoading}
          onSelectQuestion={handleSendMessage}
        />

        <div className="chat-input-container">
          <ChatInput onSendMessage={handleSendMessage} disabled={isLoading} />
        </div>
      </div>
    </div>
  );
}
